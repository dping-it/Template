package it.dping.template.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RecoveryRequest {

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

}
