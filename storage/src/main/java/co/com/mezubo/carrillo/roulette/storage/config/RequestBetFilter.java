package co.com.mezubo.carrillo.roulette.storage.config;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

import co.com.mezubo.carrillo.roulette.storage.entity.MzbUsers;
import co.com.mezubo.carrillo.roulette.storage.exception.RestExceptionMessage;
import co.com.mezubo.carrillo.roulette.storage.service.UsersServiceCrud;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

public class RequestBetFilter extends OncePerRequestFilter {

    UsersServiceCrud userService;

    public RequestBetFilter(UsersServiceCrud userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String user = resolveToken(request);
        try {
            MzbUsers userModel = (MzbUsers) userService.findByNickName(user).getBody();
            if (user != null && userModel.getCredit().compareTo(new BigDecimal(0)) > 0) {
                filterChain.doFilter(request, response);
            } else {
                final RestExceptionMessage apiError = new RestExceptionMessage(HttpStatus.FORBIDDEN, "User Dont Credit!");
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().write(convertObjectToJson(apiError));
            }
        } catch (ResponseStatusException ex) {
            final RestExceptionMessage apiError = new RestExceptionMessage(HttpStatus.FORBIDDEN, "User Dont Exist!");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write(convertObjectToJson(apiError));
            SecurityContextHolder.clearContext();
        } catch (Exception ex) {
            logger.error("Filter Section " + ex);
            SecurityContextHolder.clearContext();
        }

    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Id ")) {
            return bearerToken.substring(3);
        }
        return null;
    }
}
