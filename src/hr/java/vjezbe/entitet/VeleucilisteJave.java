package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska{

    public VeleucilisteJave(String naziv, Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
        super(naziv, predmeti, profesori, studenti, ispiti);
    }

    //DONE
    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, Integer pismeni, Integer obrana, Student student) {

            Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(ispiti, student);
            BigDecimal prosjekOcjenaNaIspitima = odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(ispiti, student));

           return (prosjekOcjenaNaIspitima.multiply(BigDecimal.valueOf(2)).add(BigDecimal.valueOf(obrana).add(BigDecimal.valueOf(pismeni)))).divide(BigDecimal.valueOf(4));
    }

    //DONE
    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {

        Integer indexStudenta = 0;
        BigDecimal najboljiProsjek = BigDecimal.valueOf(0);

        for(int i = 0;i< getIspiti().length;i++){
            BigDecimal temp = odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(getIspiti(), getStudenti()[i]));
            if(temp.compareTo(najboljiProsjek) >= 0){
                indexStudenta = i;
                najboljiProsjek = temp;
            }
        }

        return getStudenti()[indexStudenta];

    }
}
