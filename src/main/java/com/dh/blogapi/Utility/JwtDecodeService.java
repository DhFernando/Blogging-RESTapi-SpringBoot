package com.dh.blogapi.Utility;

import jdk.nashorn.internal.parser.JSONParser;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class JwtDecodeService {

    public TokenUserDetails jwtDecode() {
        String token = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest()
                .getHeader("Authorization");

        String payload = token.split("\\.")[1];

        try{

            String decodePaylaod = new String(Base64.decodeBase64(payload) , "UTF-8" ) ;

            JSONObject jOb = new JSONObject(decodePaylaod);

            String permissionLevel = jOb.getString("permissionLevel");
            String username = jOb.getString("sub");

            TokenUserDetails tokenUserDetails = new TokenUserDetails();
            tokenUserDetails.setUsername(username);
            tokenUserDetails.setPermissionLevel(permissionLevel);

            System.out.println( tokenUserDetails.getPermissionLevel() );
            System.out.println( tokenUserDetails.getUsername() );


            return tokenUserDetails;

        }catch (Exception e){
            return null;
        }
    }
}
