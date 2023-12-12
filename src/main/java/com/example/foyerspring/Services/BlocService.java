package com.example.foyerspring.Services;

import com.example.foyerspring.DAO.Entities.Bloc;
import com.example.foyerspring.DAO.Entities.Chambre;
import com.example.foyerspring.DAO.Entities.Foyer;
import com.example.foyerspring.DAO.Repositories.BlocRepository;
import com.example.foyerspring.DAO.Repositories.ChambreRepository;
import com.example.foyerspring.DAO.Repositories.FoyerRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
@Builder
@Service
@AllArgsConstructor
public class BlocService implements IBlocService{

        BlocRepository blocRepository;
        ChambreRepository chambreRepository;
        FoyerRepository foyerRepository;

        @Override
        public Bloc addBloc(Bloc b) {
                List<Chambre> chambres = (List<Chambre>) b.getChambres();
                b = blocRepository.save(b);
                for (Chambre chambre : chambres) {
                        chambre.setBloc(b);
                        chambreRepository.save(chambre);
                }
                return b; //on ajoute une ligne
        }

        @Override
        public List<Bloc> addBlocs(List<Bloc> blocs) {
                return blocRepository.saveAll(blocs); //on ajoute Des lignes dans la BD
        }

        @Override
        public Bloc editBloc(Bloc b) {
                return blocRepository.save(b); //pour faire la modification
        }

        @Override
        public List<Bloc> findAll() {
                return blocRepository.findAll();
        }

        @Override
        public Bloc findById(long id) {
                // return blocRepository.findById(id).get();
                //ou bien
                //ou bien
                return blocRepository.findById(id).orElse(Bloc.builder().idBloc(0).nomBloc("pas de bloc").build());


        }

        @Override
        public void deleteById(long id) {
                blocRepository.deleteById(id);

        }

        @Override
        public void delete(Bloc b) {
                blocRepository.delete(b);

        }

        @Override
        public List<Bloc> findByNomBloc(String nomBloc) {
                return blocRepository.findByNomBloc(nomBloc);
        }

        @Override
        public List<Bloc> findByCapaciteBloc(int capaciteBloc) {
                return blocRepository.findByCapaciteBloc(capaciteBloc);
        }

        @Override
        public List<Bloc> findByNomBlocAndCapaciteBloc(String nomBloc, int capaciteBloc) {
                return blocRepository.findByNomBlocAndCapaciteBloc(nomBloc, capaciteBloc);
        }

        @Override
        public List<Bloc> findByNomBlocIgnoreCase(String nomBloc) {
                return blocRepository.findByNomBlocIgnoreCase(nomBloc);
        }

        @Override
        public List<Bloc> findByCapaciteBlocGreaterThan(int capacite) {
                return blocRepository.findByCapaciteBlocGreaterThan(capacite);
        }

        @Override
        public List<Bloc> findByNomBlocContaining(String subString) {
                return blocRepository.findByNomBlocContaining(subString);
        }

        @Override
        public List<Bloc> findAllByOrderByNomBlocAsc() {
                return blocRepository.findAllByOrderByNomBlocAsc();
        }

        @Override
        public List<Bloc> findByNomBlocOrCapaciteBloc(String nomBloc, int capaciteBloc) {
                return blocRepository.findByNomBlocOrCapaciteBloc(nomBloc, capaciteBloc);
        }



        @Override
        public Bloc affecterChambresABloc(List<Integer> numChambre, String nomBloc) {
                Bloc b = blocRepository.getBlocByNomBloc(nomBloc);
                numChambre.forEach(numero ->{
                        Chambre c = chambreRepository.findByNumeroChambre(numero);
                        c.setBloc(b);
                        chambreRepository.save(c);

                });
                return b ;
        }

        @Override
        public Bloc desaffecterChambreABloc(long idBloc) {
                Bloc bloc = blocRepository.findById(idBloc).orElse(null);

                if (bloc != null) {
                        List<Chambre> chambres = bloc.getChambres();
                        for (Chambre chambre : chambres) {
                                chambre.setBloc(null);
                        }
                        bloc.setChambres(chambres);
                        blocRepository.save(bloc);
                }
                return bloc;
        }

        @Override
        public Bloc affecterBlocAFoyer(String nomBloc, String nomFoyer) {
                Bloc b = blocRepository.getBlocByNomBloc(nomBloc);
                Foyer f = foyerRepository.findFoyerByNomFoyer(nomFoyer);
                ////////////// PARENT HOWA BLOC
                b.setFoyer(f);
                return blocRepository.save(b);
        }

}
