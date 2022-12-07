package com.revature.P1AndrewBrittain.Util.Tokens;

import com.revature.P1AndrewBrittain.Models.Employee;
import com.revature.P1AndrewBrittain.Util.Exceptions.InvalidTokenException;
import com.revature.P1AndrewBrittain.Util.Exceptions.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import javax.crypto.spec.SecretKeySpec;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JWTUtility {

    private static Properties properties = new Properties();
    private static byte[] chocolateSaltyBytes;

    static{
        try {
            properties.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        chocolateSaltyBytes = Base64.getEncoder().encode(
                Base64.getEncoder().encode(properties.getProperty("jwt-secret").getBytes())
        );
    }

    public String createToken(Employee employee) throws IOException {


        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(String.valueOf(employee.getEmployeeEmail()))
                .setIssuer("employeeERS-AndrewBN")
                .claim("access", employee.getIsManagerTrue())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000 * 8))
                .signWith(new SecretKeySpec(chocolateSaltyBytes, "HmacSHA256"));

        return tokenBuilder.compact();
    }

    private Optional<Employee> parseToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(chocolateSaltyBytes)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Optional.of(new Employee(claims.getId()));
    }

    public boolean isTokenValid(String token){
        if(token == null || token.trim().equals("")) return false;
        return parseToken(token).isPresent();
    }

    public Employee extractTokenDetails(String token)  {
        if(!isTokenValid(token)) {
            throw new UnauthorizedException("You've not logged in an established a token");
        }
        return parseToken(token).orElseThrow(InvalidTokenException::new);
    }
}
