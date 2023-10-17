package school.sptech.validacoes;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class MusicaCriacaoDto {
    @NotBlank
    private String nomw;

    private String album;
    private LocalDate date;
}
