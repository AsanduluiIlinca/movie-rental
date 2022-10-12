package com.movierental.repository;

import com.movierental.model.Client;

import java.util.List;

public interface ClientRepositoryCustom {
    List<Client> searchClientByName(String name);
}
