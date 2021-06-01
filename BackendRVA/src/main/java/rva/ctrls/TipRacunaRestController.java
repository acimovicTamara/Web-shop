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
import rva.jpa.TipRacuna;
import rva.repository.TipRacunaRepository;

@RestController
@CrossOrigin
@Api(tags= {"Tip racuna CRUD operacije"})
public class TipRacunaRestController {
	@Autowired
	private TipRacunaRepository tipRacunaRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("tipRacuna")
	@ApiOperation(value="Vraca kolekciju svih tipova racuna iz baze podataka")
	public Collection<TipRacuna> getTipoviRacuna(){
		return tipRacunaRepository.findAll();
	}
	
	@GetMapping("tipRacuna/{id}")
	@ApiOperation(value="Vraca tip racuna iz baze podataka cija je id vrednost prosledjena kao path varijabla")
	public TipRacuna getTipRacuna(@PathVariable("id") Integer id) {
		return tipRacunaRepository.getOne(id);
	}
	
	@GetMapping("tipRacunaNaziv/{naziv}")
	@ApiOperation(value="Vraca kolekciju svih tipova racuna iz baze podataka koji u nazivu sadze string koji je prosledjen kao path varijabla")
	public Collection<TipRacuna> getTipRacunaByNaziv(@PathVariable("naziv") String naziv)
	{
		return tipRacunaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@PostMapping("tipRacuna")
	@ApiOperation(value="Upisuje tip racuna u bazu podataka")
	public ResponseEntity<TipRacuna> insertTipRacuna(@RequestBody TipRacuna tipRacuna){
		if(!tipRacunaRepository.existsById(tipRacuna.getId())) {
			tipRacunaRepository.save(tipRacuna);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	@PutMapping("tipRacuna")
	@ApiOperation(value="Modifikuje postojeci tip racuna u bazi podataka")
	public ResponseEntity<TipRacuna> updateTipRacuna(@RequestBody TipRacuna tipRacuna){
		if(!tipRacunaRepository.existsById(tipRacuna.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		tipRacunaRepository.save(tipRacuna);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@DeleteMapping("tipRacuna/{id}")
	public ResponseEntity<TipRacuna> deleteTipRacuna(@PathVariable("id") Integer id){
		if(!tipRacunaRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		jdbcTemplate.execute("delete from racun where tip_racuna="+id);
		tipRacunaRepository.deleteById(id);
		if(id==5)
		   jdbcTemplate.execute("INSERT INTO \"tip_racuna\" (\"id\",\"naziv\",\"opis\",\"oznaka\")"
				   +"VALUES (-32,'devizni','Moze se koristiti za prilive iz inostranstva','dev5') ");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
