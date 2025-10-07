package com.senac.bunnyhut.dto.response;

import java.time.LocalDateTime;

public class Transaction_CoinDTOResponse {
    private Integer transaction_coin_id;
    private Integer userId;
    private Enum transaction_type;
    private Integer transaction_value;
    private String transaction_description;
    private LocalDateTime transaction_date;

}