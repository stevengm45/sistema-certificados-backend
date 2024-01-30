package com.agg.certificados.services.botaderoServices;


import com.agg.certificados.repositories.UserRepository;

import com.agg.certificados.dtos.request.BotaderoRequestDto;
import com.agg.certificados.dtos.response.BotaderoResponseDto;
import com.agg.certificados.entity.Botadero;
import com.agg.certificados.entity.User;
import com.agg.certificados.repositories.botaderoRepository.IBotaderoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BotaderoService implements IBotaderoService{
    @Autowired
    private IBotaderoRepository botaderoRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int save(BotaderoRequestDto dto) {

        Botadero entity = new Botadero(
                dto.id_botadero,
                dto.city,
                dto.property_name,
                LocalDate.now(),
                userRepository.findById(dto.user_id).orElse(null),
                dto.status
        );

        botaderoRepository.save(entity);

        return entity.id_botadero;
    }


    public Optional<Botadero> getById(int id){
        Optional<Botadero> entity = botaderoRepository.findById(id);
        return entity;
    }
    public List<BotaderoResponseDto> getAll(){
        List<Botadero> entities = botaderoRepository.findAll();

        List<BotaderoResponseDto> listDto = new ArrayList<>();

        for (Botadero entity:entities) {
            //Obtiene el usuario
            Optional<User> user = userRepository.findById(entity.getUser_id().getId());
            //Setea el usuario
            entity.setUser_id(user.orElse(null));
            //Agrega a la lista
            listDto.add(new BotaderoResponseDto(entity));
        }

        return listDto;
    }

    public List<BotaderoResponseDto> getAllActive(){
        List<Botadero> entities = botaderoRepository.findAll();

        List<BotaderoResponseDto> listDto = new ArrayList<>();

        for (Botadero entity:entities) {

            if (entity.status == true){

                //Obtiene el usuario
                Optional<User> user = userRepository.findById(entity.getUser_id().getId());

                //Setea el usuario
                entity.setUser_id(user.orElse(null));

                //Agrega a la lista
                listDto.add(new BotaderoResponseDto(entity));
            }

        }

        return listDto;
    }

    public boolean delete(int id) {
        botaderoRepository.deleteById(id);
        return !botaderoRepository.existsById(id);
    }

    @Transactional
    public boolean update(BotaderoRequestDto dto, int id) {
        Optional<Botadero> botadero = botaderoRepository.findById(id);
        Optional<User> user = userRepository.findById(dto.user_id);

        if (botadero == null) return false;
        if (user == null) return false;

        Botadero bot = botadero.get();

        bot.setCity(dto.city);
        bot.setCreate_date(botadero.get().create_date);
        bot.setStatus(dto.status);
        bot.setProperty_name(dto.property_name);
        bot.setUser_id(user.orElse(null));
        bot.setId_botadero(botadero.get().id_botadero);

        botaderoRepository.save(bot);

        return true;
    }

    @Override
    public boolean updateStatus(boolean status, int id) {
        Optional<Botadero> botadero = botaderoRepository.findById(id);
        Optional<User> user = userRepository.findById(botadero.get().user_id.getId());


        Botadero bot = botadero.get();

        bot.setCity(botadero.get().city);
        bot.setCreate_date(botadero.get().create_date);
        bot.setStatus(status);
        bot.setProperty_name(bot.getProperty_name());
        bot.setUser_id(user.orElse(null));
        bot.setId_botadero(botadero.get().id_botadero);

        botaderoRepository.save(bot);
        return true;
    }

}
