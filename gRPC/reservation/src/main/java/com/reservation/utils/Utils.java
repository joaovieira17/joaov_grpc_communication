package com.reservation.utils;

import com.reservation.model.ReservationItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    //To guarantee that there are no repeated sandwiches (if they want more of a specific sandwich they should change quantity)
    public static void verifyDuplicatedItems(List<ReservationItem> items){
        Set<String> uniqueSandwichIds = new HashSet<>();
        for (int i=0; i<items.size();i++) {
            String sandwichId = items.get(i).getSandwichId().toString();
            if (uniqueSandwichIds.contains(sandwichId)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This sandwichId is repeated: " + sandwichId);
            }
            uniqueSandwichIds.add(sandwichId);
        }
    }
}
