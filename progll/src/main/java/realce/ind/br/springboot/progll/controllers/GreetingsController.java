package realce.ind.br.springboot.progll.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import realce.ind.br.springboot.progll.model.Usuario;
import realce.ind.br.springboot.progll.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */

@RestController
public class GreetingsController {
    
	/**
     *
     * @param name the name to greet
     * @return greeting text
     */
	
  //  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
   // @ResponseStatus(HttpStatus.OK)
    //public String greetingText(@PathVariable String name) {
     //   return "Testes de Servidor " + name + " !é um Teste! ";
        
   // }
    
   // @RequestMapping(value = "Realce/{texto}", method = RequestMethod.GET)
   // @ResponseStatus(HttpStatus.OK)
   // public String mostraSegundo(@PathVariable String texto){
    //	return "Esse é o texto:  " + texto + ". Estamos no segundo teste!";
    	
   // }
    
    @Autowired
    private UsuarioRepository usuarioRepository ;
    @RequestMapping(value= "testeGravar/{nome}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String testeGravar (@PathVariable String nome) {
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuarioRepository.save(usuario);
    	return "Gravado";
    }
    
    @GetMapping(value = "listatodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>>listaUsuario(){
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK) ;
    }
    
    @RequestMapping (value = "salvar")
    @ResponseBody
    public ResponseEntity <Usuario> salvar (@RequestBody Usuario usuario){
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
   @RequestMapping (value = "delete")
   @ResponseBody
   public ResponseEntity<String> delete(@RequestParam Long iduser){
	   usuarioRepository.deleteById(iduser);
	   return new ResponseEntity<String>("Usuario excluido com sucesso", HttpStatus.OK);
   }
   
   @GetMapping (value = "buscaruserid") 
   @ResponseBody
   public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser")Long iduser){
	   Usuario usuario = usuarioRepository.findById(iduser).get();
	   return new ResponseEntity <Usuario> (usuario, HttpStatus.OK);
   }
   
   
   @PutMapping(value = "atualizar")
   @ResponseBody
   public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
   		if(usuario.getId()==null) {
   		return new ResponseEntity<String>("Id não informado para atualizar", HttpStatus.OK);
   		}
   		Usuario user = usuarioRepository.saveAndFlush(usuario);
   		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
   }
   @GetMapping(value = "buscarpornome")
   @ResponseBody
   public ResponseEntity<List<Usuario>> buscarpornome(@RequestParam(name = "nome")String nome){
	   List<Usuario> usuario = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
	   return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
	   
   }
  }
