package com.reviews.grpcService;

import com.joao.sandwich.SandwichResponse;

import java.util.UUID;

public interface SandwichGrpcService {

SandwichResponse getSandwich(UUID sandwichId);

}
