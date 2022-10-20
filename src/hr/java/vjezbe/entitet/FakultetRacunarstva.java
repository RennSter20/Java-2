package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {

    public FakultetRacunarstva(String naziv, Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
        super(naziv, predmeti, profesori, studenti, ispiti);
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, Integer pismeni, Integer diplomski, Student student) {
        Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(ispiti, student);
        BigDecimal prosjekOcjenaNaIspitima = odrediProsjekOcjenaNaIspitima(ispitiStudenta);

        return (prosjekOcjenaNaIspitima.multiply(BigDecimal.valueOf(3)).add(BigDecimal.valueOf(diplomski).add(BigDecimal.valueOf(pismeni)))).divide(BigDecimal.valueOf(5));

    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {

        int broj = 0;
        Integer[] brojIzvrsnihOcjena = new Integer[getStudenti().length];
        for(int i = 0;i< getIspiti().length;i++) brojIzvrsnihOcjena[i] = 0;

        int lastIndex = 0;
        for(Ispit ispit : getIspiti()){
            for(int i = 0;i< getStudenti().length;i++){
                if(ispit.getStudent() == getStudenti()[i] && ispit.getOcjena() == 5){
                    brojIzvrsnihOcjena[i]++;
                    lastIndex = i;
                }
            }
        }

        return getStudenti()[lastIndex];



    }

    @Override
    public Student odrediStudentaZaRektorovuNagradu() {

        BigDecimal[] prosjeci = new BigDecimal[getStudenti().length];

        for(int i = 0;i< getStudenti().length;i++){
            prosjeci[i] = odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(getIspiti(), getStudenti()[i]));
        }

        Integer najbolji = 0;

        for(int i = 1;i< getStudenti().length;i++){
            if(prosjeci[i].compareTo(prosjeci[najbolji]) > 0 && getStudenti()[i].getDatumRodjenja().isAfter(getStudenti()[najbolji])){
                najbolji = i;
            }
        }
        
        return getStudenti()[najbolji];

    }
}
