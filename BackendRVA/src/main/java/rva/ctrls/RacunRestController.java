package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Klijent;
import rva.jpa.Racun;
import rva.jpa.TipRacuna;
import rva.repository.KlijentRepository;
import rva.repository.RacunRepository;
import rva.repository.TipRacunaRepository;

@RestController
@CrossOrigin
@Api(tags= {"Racun CRUD operacije"})
public class RacunRestController {
	
	@Autowired
	private RacunRepository racunRepository;
	
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TipRacunaRepository tipRacunaRepository;
	
	@Autowired
	private KlijentRepository klijentRepository;
	
	@GetMapping("racun")
	@ApiOperation(value="Vraca kolekciju svih racuna iz baze podataka")
	public Collection<Racun> getRacuni(){
		return racunRepository.findAll();
	}
	
	@GetMapping("racun/{id}")
	@ApiOperation(value="Vraca racun iz baze podataka cija je id vrednost prosledjena kao path varijabla")
	public ResponseEntity<Racun> getRacun(@PathVariable("id") Integer id) {
		Racun racun= racunRepository.getOne(id);
		return new ResponseEntity<Racun>(racun,HttpStatus.OK);
	}

	
	@GetMapping("racuniPoTipuRacunaId/{id}")
	@ApiOperation(value="Vraca kolekciju svih racuna iz baze podataka koji pripadaju tipu racuna cija id vrednost je prosledjena kao path varijabla")
	public Collection<Racun> racuniPoTipuRacunaId(@PathVariable("id") int id) {
	    TipRacuna tipRacuna=tipRacunaRepository.getOne(id);
	    return racunRepository.findByTipRacuna(tipRacuna);
	
	
	}
	

	@GetMapping("racuniPoKlijentId/{id}")
	@ApiOperation(value="Vraca kolekciju svih racuna iz baze podataka koje imaju klijenti cija id vrednost je prosledjena kao path varijabla")
	public Collection<Racun> racuniPoKlijentId(@PathVariable("id") int id) {
	    Klijent klijent=klijentRepository.getOne(id);
	    return racunRepository.findByKlijent(klijent);
	
	}
	
	@PostMapping("racun")
	@ApiOperation(value="Upisuje racun u bazu podataka")
	public ResponseEntity<Racun> insertRacun(@RequestBody Racun racun){
		if(!racunRepository.existsById(racun.getId())) {
			racunRepository.save(racun);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PutMapping("racun")
	@ApiOperation(value="Modifikuje postojeci racun u bazi podataka")
	public ResponseEntity<Racun> updateRacun(@RequestBody Racun racun){
		if(!racunRepository.existsById(racun.getId()))
			return new ResponseEntity<Racun>(HttpStatus.NO_CONTENT);
		racunRepository.save(racun);
		return new ResponseEntity<Racun>(HttpStatus.OK);
	}
	
	@DeleteMapping("racun/{id}")
	@ApiOperation(value="Brise racun iz baze podataka cija vrednost id je prosledjena kao path varijabla")
	public ResponseEntity<Racun> deleteKlijent(@PathVariable("id") Integer id){
		if(!racunRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		racunRepository.deleteById(id);
		if(id==8)
		   jdbcTemplate.execute("INSERT INTO \"racun\" (\"id\",\"naziv\",\"opis\",\"oznaka\",\"klijent\",\"tip_racuna\")"
				   +"VALUES (8,'Studentski racun','Racun namjenjen studentima','studRac3',1,4) ");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	
	
	
	

}
