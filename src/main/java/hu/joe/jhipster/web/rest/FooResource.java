package hu.joe.jhipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import hu.joe.jhipster.domain.Foo;
import hu.joe.jhipster.repository.FooRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Foo.
 */
@RestController
@RequestMapping("/app")
public class FooResource {

    private final Logger log = LoggerFactory.getLogger(FooResource.class);

    @Inject
    private FooRepository fooRepository;

    /**
     * POST  /rest/foos -> Create a new foo.
     */
    @RequestMapping(value = "/rest/foos",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Foo foo) {
        log.debug("REST request to save Foo : {}", foo);
        fooRepository.save(foo);
    }

    /**
     * GET  /rest/foos -> get all the foos.
     */
    @RequestMapping(value = "/rest/foos",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Foo> getAll() {
        log.debug("REST request to get all Foos");
        return fooRepository.findAll();
    }

    /**
     * GET  /rest/foos/:id -> get the "id" foo.
     */
    @RequestMapping(value = "/rest/foos/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Foo> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Foo : {}", id);
        Foo foo = fooRepository.findOne(id);
        if (foo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foo, HttpStatus.OK);
    }

    /**
     * DELETE  /rest/foos/:id -> delete the "id" foo.
     */
    @RequestMapping(value = "/rest/foos/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Foo : {}", id);
        fooRepository.delete(id);
    }
}
