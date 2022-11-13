package com.e_music.project_emusic.entities.DTOs;

import com.e_music.project_emusic.entities.Instrument;
import com.e_music.project_emusic.entities.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {

    private String payment_method;
    private Double total_price;
    private User user;
    private Instrument instrument;
}
