package id.pradana.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.pradana.ems.model.Title;

public interface TitleRepository extends JpaRepository<Title, Long> {
}
