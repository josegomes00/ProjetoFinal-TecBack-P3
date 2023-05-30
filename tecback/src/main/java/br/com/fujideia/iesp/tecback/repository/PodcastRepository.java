package br.com.fujideia.iesp.tecback.repository;

import br.com.fujideia.iesp.tecback.model.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodcastRepository extends JpaRepository<Podcast, Integer> {

}
