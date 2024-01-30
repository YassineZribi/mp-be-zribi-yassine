package soa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import soa.entities.Categorie;
import soa.repository.CategorieRepository;

@RestController // pour déclarer un service web de type REST
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categories")  //    http://localhost:8080/produits
public class CategorieRESTController {
    @Autowired // pour l'injection de dépendances
    private CategorieRepository categorieRepos;

    //  Message d'accueil
    //  http://localhost:8080/categories/index  (GET)
    @GetMapping(value ="/index" )
    public String accueil() {
        return "BienVenue au service Web REST 'categories'.....";
    }

    //  Afficher la liste des categories
    //  http://localhost:8080/categories/ (GET)

    @GetMapping(
            // spécifier le path de la méthode
            value= "/",
            // spécifier le format de retour en XML
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public  List<Categorie> getAllCategories() {
        return categorieRepos.findAll();

    }

    //  Afficher une categorie en spécifiant son 'id'
    //  http://localhost:8080/categories/{id} (GET)
    @GetMapping(
            // spécifier le path de la méthode qui englobe un paramètre
            value= "/{id}" ,
            // spécifier le format de retour en XML
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public Categorie getCategorie(@PathVariable Long id) {
        Categorie c =categorieRepos.findById(id).get();
        return c;
    }

    // Supprimer une categorie par 'id' avec la méthode 'GET'
    //  http://localhost:8080/categories/delete/{id}  (GET)
    @GetMapping(
            // spécifier le path de la méthode
            value = "/delete/{id}")
    public void deleteCategorie(@PathVariable Long id)
    {
        categorieRepos.deleteById(id);
    }

    //  ajouter une categorie avec la méthode "POST"
    //  http://localhost:8080/categories/   (POST)
    @PostMapping(
            // spécifier le path de la méthode
            value = "/"  ,
            //spécifier le format de retour
            produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public Categorie saveCategorie(@RequestBody Categorie c)
    {
        return categorieRepos.save(c);
    }

    //  modifier une categorie avec la méthode "PUT"
    //  http://localhost:8080/categories/   (PUT)
    @PutMapping(
            // spécifier le path de la méthode
            value = "/"  ,
            //spécifier le format de retour
            produces = { MediaType.APPLICATION_JSON_VALUE  }
    )
    public Categorie updateCategorie(@RequestBody Categorie c)
    {
        return categorieRepos.save(c);
    }

    // Supprimer une catégorie avec la méthode 'DELETE'
    //  http://localhost:8080/categories/   (DELETE)
    @DeleteMapping(
            // spécifier le path de la méthode
            value = "/")
    public void deleteCategorie(@RequestBody Categorie c)
    {
        categorieRepos.delete(c);
    }

}
