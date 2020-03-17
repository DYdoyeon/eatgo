package kr.co.fastcampus.eatgos.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RegionTests {

    @Test
    public void creatation(){
        Region region = Region.builder().name("서울").build();

        assertThat(region.getName(),is("서울"));
    }
}