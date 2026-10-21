package com.senac.montadora.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static Map<String, String>
                parserMensagem(String mensagem) {
        Map<String, String> saida = new HashMap<>();
        saida.put("mensagem", mensagem);

        return saida;
    }
}
