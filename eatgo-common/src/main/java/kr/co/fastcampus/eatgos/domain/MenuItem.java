package kr.co.fastcampus.eatgos.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    private Long restaurantId;
    private String name;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean destroy;

}
