package com.tp1act1.bookstore.Repository;

import com.tp1act1.bookstore.Domain.CommandHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommandHistoryRepository extends JpaRepository<CommandHistory,Long> {
public List<CommandHistory> findCommandHistoriesByCustomer_Id(Long id);
}
