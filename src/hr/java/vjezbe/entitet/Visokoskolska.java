package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Arrays;

public interface Visokoskolska {

    BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta (Ispit[] ispiti, Integer pismeni, Integer obrana, Student student);

    //DONE
    private Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti){

        Integer broj = 0;
        Ispit[] tempIspiti = new Ispit[broj];

        for(int i = 0;i< ispiti.length;i++){
            if(ispiti[i].getOcjena() > 1){
                broj++;
                tempIspiti = Arrays.copyOf(tempIspiti, broj);
                tempIspiti[broj-1] = ispiti[i];
            }
        }
        return tempIspiti;
    }

    //DONE
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

    //DONE
    default  Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti, Student student) {

        int brojIspita = 0;

        Ispit[] tempIspiti = new Ispit[brojIspita];
        for(int i = 0;i<ispiti.length;i++){
            if(ispiti[i].getStudent() == student){
                brojIspita++;
                tempIspiti = Arrays.copyOf(tempIspiti, brojIspita);
                tempIspiti[brojIspita-1] = ispiti[i];
            }
        }
        return tempIspiti;
    }


}
