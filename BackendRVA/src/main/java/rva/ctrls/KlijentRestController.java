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
import rva.jpa.Kredit;
import rva.repository.KlijentRepository;
import rva.repository.KreditRepository;

@RestController 
@CrossOrigin
@Api(tags= {"Klijent CRUD operacije"})
public class KlijentRestController {
	
	@Autowired
	private KlijentRepository klijentRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KreditRepository kreditRepository;
	
	@GetMapping("klijent")  
	@ApiOperation(value="Vraca kolekciju svih klijenata iz baze podataka")
	public Collection<Klijent> getKlijenti(){
		return klijentRepository.findAll();
	}
	
	@GetMapping("klijent/{id}")
	@ApiOperation(value="Vraca klijenta iz baze podataka cija vrednost id je prosledjena kao path varijabla")
	public Klijent getKlijent(@PathVariable("id") Integer id) {
		return klijentRepository.getOne(id);
	}
	
	@GetMapping("klijentLicnaKarta/{brojLk}")
	@ApiOperation(value="Vraca klijenta iz baze podataka ciji je broj licne karte prosledjen kao path varijabla")
	public Klijent getKlijentByBrojLk(@PathVariable("brojLk") Integer brojLk) {
		return klijentRepository.findByBrojLk(brojLk);
	}
	

	
	@GetMapping("klijentiPoKredituId/{id}")
	@ApiOperation(value="Vraca kolekciju svih klijenata iz baze podataka koji imaju kredit cija id vrednost je prosledjena kao path varijabla")

	public Collection<Klijent> getKlijentiByKredit(@PathVariable("id") int id)
	{
		Kredit kredit= kreditRepository.getOne(id);
		return klijentRepository.findByKredit(kredit);
	}
	
	

	@PostMapping("klijent")
	@ApiOperation(value="Upisuje klijenta u bazu podataka")
	public ResponseEntity<Klijent> insertKlijent(@RequestBody Klijent klijent){
		if(!klijentRepository.existsById(klijent.getId())) { //proverava da li postoji klijent saq prosledjenim id-em
			klijentRepository.save(klijent); //ako ne postoji bice sacuvan u repository klijent sa tim id-em
			return new ResponseEntity<>(HttpStatus.OK); //vraca status OK
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT); //ako postoji, vraca conflict jer ne mogu da postoje dva klijenta sa istim id-em
	}
	
	@PutMapping("klijent")
	@ApiOperation(value="Modifikuje prosledjenog klijenta u bazi podataka")
	public ResponseEntity<Klijent> updateKlijent(@RequestBody Klijent klijent){
		if(!klijentRepository.existsById(klijent.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); //ako ne postoji klijnet ne moze se update-ovati
		klijentRepository.save(klijent); //ako postoji, sacuvace izmene
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("klijent/{id}")
	@ApiOperation(value="Brise klijenta iz baze podataka cija je id vrednost prosledjena kao path varijabla")
	public ResponseEntity<Klijent> deleteKlijent(@PathVariable("id") Integer id){
		if(!klijentRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		klijentRepository.deleteById(id); //u suprotnom ce pozvati iz KlijentRepository deletebyId metodu i proslediti id koji smo  prosledili kao parametar ove metode
		if(id==10) //ukoliko prosledjeni ID ima vrednost 10, zelimo da se ponovo uradi insert u bazu podataka,da se izvrsi INSERT INTO naredba
		   jdbcTemplate.execute("INSERT INTO \"klijent\" (\"id\",\"broj_lk\",\"ime\",\"prezime\",\"kredit\")"
				   +"VALUES (10,'665338','Marko','Lukic',1) ");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
