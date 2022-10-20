package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public interface Visokoskolska {

    BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta (Ispit[] ispiti, Integer pismeni, Integer obrana, Student student);

    private Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti){

        Integer broj = 0;


        for(int i = 0;i< ispiti.length;i++){
            if(ispiti[i].getOcjena() > 1){
                broj++;
            }
        }

        int indx = 0;
        Ispit[] tempIspiti = new Ispit[broj];
        for(int i = 0;i<ispiti.length;i++){
            if(ispiti[i].getOcjena() > 1){
                tempIspiti[indx] = ispiti[i];
                indx++;
            }
        }

        return tempIspiti;
    }

    default BigDecimal odrediProsjekOcjenaNaIspitima(Ispit[] ispiti){

        Integer suma = 0;
        Integer broj = 0;

        for(int i = 0;i<ispiti.length;i++){
            if(ispiti[i].getOcjena() > 1){
                suma+= ispiti[i].getOcjena();
                broj++;
            }
        }

        return BigDecimal.valueOf(suma/broj);
    }

    default  Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti, Student student) {

        int brojIspita = 0;

        for(int i = 0;i<ispiti.length;i++){
            if(ispiti[i].getStudent() == student){
                brojIspita++;
            }
        }

        int indx = 0;
        Ispit[] tempIspiti = new Ispit[brojIspita];

        for(int i = 0;i<ispiti.length;i++){
            if(ispiti[i].getStudent() == student){
                tempIspiti[indx] = ispiti[i];
                indx++;
            }
        }

        return tempIspiti;

    }


}
