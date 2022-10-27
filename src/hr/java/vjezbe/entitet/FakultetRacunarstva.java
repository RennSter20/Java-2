package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {

    public FakultetRacunarstva(String naziv, Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
        super(naziv, predmeti, profesori, studenti, ispiti);
    }

    //DONE
    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, Integer pismeni, Integer diplomski, Student student) {
        Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(ispiti, student);
        BigDecimal prosjekOcjenaNaIspitima = odrediProsjekOcjenaNaIspitima(ispitiStudenta);

        return (prosjekOcjenaNaIspitima.multiply(BigDecimal.valueOf(3)).add(BigDecimal.valueOf(diplomski).add(BigDecimal.valueOf(pismeni)))).divide(BigDecimal.valueOf(5));

    }

    //DONE
    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {

        Integer[] brojIzvrsnihOcjena = new Integer[getStudenti().length];
        for(int i = 0;i< getIspiti().length;i++) brojIzvrsnihOcjena[i] = 0;


        for(Ispit ispit : getIspiti()){
            for(int i = 0;i< getStudenti().length;i++){
                if(ispit.getStudent() == getStudenti()[i] && ispit.getOcjena() == 5){
                    brojIzvrsnihOcjena[i]++;
                }
            }
        }

        int lastIndex = brojIzvrsnihOcjena.length-1;
        for(int i = brojIzvrsnihOcjena.length-1;i>-1;i--){
            if(brojIzvrsnihOcjena[i] > brojIzvrsnihOcjena[lastIndex]){
                lastIndex = i;
            }
        }

        return getStudenti()[lastIndex];
    }

    //DONE
    @Override
    public Student odrediStudentaZaRektorovuNagradu() {

        BigDecimal[] prosjeci = new BigDecimal[getStudenti().length];

        for(int i = 0;i< getStudenti().length;i++){
            prosjeci[i] = odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(getIspiti(), getStudenti()[i]));
        }

        Integer najbolji = 0;

        for(int i = 1;i< getStudenti().length;i++){
            if(prosjeci[i].compareTo(prosjeci[najbolji]) > 0){
                najbolji = i;
            }else if(prosjeci[i].compareTo(prosjeci[najbolji]) == 0){
                if(getStudenti()[i].getDatumRodjenja().isAfter(getStudenti()[i].getDatumRodjenja())){
                    najbolji = i;
                }
            }
        }
        
        return getStudenti()[najbolji];

    }
}
