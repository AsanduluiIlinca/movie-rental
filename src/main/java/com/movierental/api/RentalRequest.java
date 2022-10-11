package com.movierental.api;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RentalRequest {

    @NotNull
    private Long clientId;

    @NotNull
    private Long movieId;

}
