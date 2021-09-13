package proyecto2.mtsolutions.dao;

import org.apache.ibatis.annotations.Mapper;
import proyecto2.mtsolutions.dto.TokenDTO;

@Mapper
public interface TokenDAO {
    int insertToken(TokenDTO tokenDTO);

    int updateToken(TokenDTO tokenDTO);

    int deleteToken(String uuid);

    int deleteTokenByUsername(String username);

    TokenDTO tokenByValue(String uuid);

    TokenDTO tokenByUsername(String username);
}
