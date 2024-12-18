package com.example.orderservice.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.orderservice.dto.response.JwtErrorResponseDto;
import com.example.orderservice.exceptions.BadCredentialsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String INVALID_TOKEN = "Invalid_Token";

//  @Value("${jwt.secret}")
//  private String secret;

  @Override
  public void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    String secret = "pejFQ7VibeTQXuMs0T/XiNFUHz5ZrITrzG9LBTDujsCEOqE6iR6+X8R8rP0V88Gc";

    try {
      String jwt = extractJwtFromRequest(request);
      if (!jwt.isEmpty()) {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        Algorithm algorithm = Algorithm.HMAC256(keyBytes);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(jwt);
        String firstname = decodedJWT.getSubject();
        request.setAttribute("firstname", firstname);

        if (request.getRequestURI().contains("api/v1/admin")
            && !decodedJWT.getClaim("role").asString().equals("ADMIN")) {
          response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
          return;
        }
      }

    } catch (BadCredentialsException e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      new ObjectMapper()
          .writeValue(
              response.getOutputStream(), new JwtErrorResponseDto("Please login", e.getMessage()));
      return;
    } catch (JWTVerificationException e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      new ObjectMapper()
          .writeValue(
              response.getOutputStream(), new JwtErrorResponseDto("Please login", e.getMessage()));
      return;
    }
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getServletPath();
    return path.contains("/swagger-ui") || path.contains("/v3/api-docs");
  }

  public String extractJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
    if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
      return bearerToken.substring(TOKEN_PREFIX.length());
    }
    throw new BadCredentialsException(INVALID_TOKEN);
  }
}
