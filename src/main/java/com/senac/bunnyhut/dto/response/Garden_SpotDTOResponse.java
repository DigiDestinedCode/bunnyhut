package com.senac.bunnyhut.dto.response;

import java.time.LocalDateTime;

public class Garden_SpotDTOResponse {
    private Integer garden_spot_id;
    private Integer gardenId;
    private Integer plantId;
    private LocalDateTime planted_at;
    private Enum growth_stage;
}
