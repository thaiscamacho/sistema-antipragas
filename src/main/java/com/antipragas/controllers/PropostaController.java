package com.antipragas.controllers;

import com.antipragas.models.Endereco;
import com.antipragas.models.Praga;
import com.antipragas.models.Proposta;
import com.antipragas.models.Usuario;
import com.antipragas.models.enums.Frequencia;
import com.antipragas.models.enums.StatusProposta;
import com.antipragas.models.enums.Tipo;
import com.antipragas.services.PragaService;
import com.antipragas.services.PropostaService;
import com.antipragas.services.UsuarioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author Thais Camacho
 */

@Controller
@RequestMapping("/proposta")
public class PropostaController {

    private Logger LOGGER = Logger.getLogger(PropostaController.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PragaService pragaService;

    @Autowired
    private PropostaService propostaService;

    @RequestMapping("/visualizar")
    public ModelAndView goProposta(){
        Map<String, Object> model = new HashMap<String, Object>();
        Usuario usuario = null;
        Set enderecos = null;

        List pragas = pragaService.findAll();

        SecurityContext context = SecurityContextHolder.getContext();
        if(context instanceof SecurityContext)
        {
            Authentication authentication = context.getAuthentication();
            if(authentication instanceof Authentication)
            {
                usuario = usuarioService.findByEmail(authentication.getName());
                enderecos = usuario.getEnderecos();
            }
        }

        model.put("pragas", pragas);
        model.put("enderecos", enderecos);

        return new ModelAndView("/outros/nova_proposta", model);
    }


    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String registrarProposta(@RequestParam String endereco,
                                    @RequestParam String frequencia,
                                    @RequestParam String qtd,
                                    @RequestParam String tipo,
                                    @RequestParam String praga,
                                    @RequestParam String descricao){

        Usuario usuario = null;
        Set<Endereco> enderecos = null;
        Set<Praga> pragas = new HashSet<Praga>();
        Proposta proposta = new Proposta();

        SecurityContext context = SecurityContextHolder.getContext();
        if(context instanceof SecurityContext)
        {
            Authentication authentication = context.getAuthentication();
            if(authentication instanceof Authentication)
            {
                usuario = usuarioService.findByEmail(authentication.getName());
                enderecos = usuario.getEnderecos();
            }
        }

        if(LOGGER.isInfoEnabled()){
            LOGGER.info(String.format("Nova proposta pelo usuário: [%s]", usuario.getEmail()));
        }


        proposta.setQuantidade(Integer.parseInt(qtd));
        proposta.setTipo(Tipo.valueOf(tipo));
        proposta.setDescricao(descricao);
        proposta.setFrequencia(Frequencia.valueOf(frequencia));
        proposta.setStatus(StatusProposta.STATUS_PROPOSTA_PENDENTE);


        String array_pragas[] = praga.split(",");

        for(int i = 0; i < array_pragas.length; i++){
            if(!array_pragas[i].equals("0")){
                pragas.add(pragaService.findById(Long.parseLong(array_pragas[i])));
            }
        }

        for(Endereco end : enderecos){
            if(end.getId() == Long.parseLong(endereco)){
                proposta.setEndereco(end);
            }
        }

        proposta.setUsuario(usuario);
        proposta.setPragas(pragas);

        propostaService.create(proposta);

        return "redirect:/usuario/painel";
    }
}