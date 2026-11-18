package org.example.regionkommunee25a.repositories;

import jakarta.transaction.Transactional;
import org.example.regionkommunee25a.model.Kommune;
import org.example.regionkommunee25a.model.Region;
import org.example.regionkommunee25a.service.ApiServiceGetKommuner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegionRepositoryTest {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;

    @Test
    void test5Regioner() {
        List<Region> regioner = regionRepository.findAll();
        assertEquals(7, regioner.size());
    }

    @Test
    @Transactional
    void testRegionNordjylland() {
        Optional<Region> regionOpt = regionRepository.findById("1081");
        if (regionOpt.isPresent()) {
            Region region = regionOpt.get();
            Set<Kommune> kommuner = region.getKommuner();
            assertEquals(11, kommuner.size());
        } else {
            fail("Region Nordjylland not found");
        }
    }

    @Test
    void testKommunerStartsWithA() {
        List<Kommune> kommuner = apiServiceGetKommuner.getKommunerStartsWith('A');
        assertEquals(2, kommuner.size());
    }

    @DisplayName("TestStartsMed")
    @ParameterizedTest
    @MethodSource("kommunedatastartswith")
    void testStartsWithParm(char c, int cnt) {
        List<Kommune> lstKom = apiServiceGetKommuner.getKommunerStartsWith(c);
        assertEquals(cnt, lstKom.size());
    }

    private static Stream<Arguments> kommunedatastartswith2() {
        return Stream.of(
                Arguments.of('A', 2),
                Arguments.of('B', 1),
                Arguments.of('C', 0),
                Arguments.of('D', 0)
        );
    }

    public static Object[][] kommunedatastartswith() {
        return new Object[][] {
                {"A", 2},
                {"B", 1},
                {"C", 0},
                {"D", 0}
        };
    }



}