package haras;

public class Program {

public static void main(String[] args) {

Cheval unCheval = new Cheval();

unCheval.setNom("Stewball");

unCheval.setAge(3);

unCheval.setPoids(500);

unCheval.setTaille(1.50f);

unCheval.setVitesses(20);

System.out.println(unCheval.versChaine());

unCheval.trotter();

System.out.println("Le cheval trotte à "+ unCheval.getVitesses() +" km/h.");

unCheval.courir();

System.out.println("Le cheval court à "+ unCheval.getVitesses()+" km/h.");

unCheval.arreter();

System.out.println("Le cheval s'arrete à "+ unCheval.getVitesses()+" km/h.");

}

}


Cheval.java :

package haras;

public class Cheval {

private String nom;

private int age;

private float taille;

private int poids;

private int vitesses;

public Cheval() {

}

public Cheval(String nom, int age,float taille, int poids, int vitesses) {

this.nom = nom;

this.age = age;

this.taille = taille;

this.poids = poids;

this.vitesses = vitesses;

}

public void setNom(String unNom) {

this.nom = unNom;

}

public void setAge(int age) {

this.age = age;

}

public void setTaille(float taille) {

this.taille = taille;

}

public void setPoids(int poids) {

this.poids = poids;

}

public void setVitesses(int vitesses) {

this.vitesses = vitesses;

}

public String getNom() {

return nom;

}

public int getAge() {

return age;

}

public float getTaille() {

return taille;

}

public int getPoids() {

return poids;

}

public int getVitesses() {

return vitesses;

}

private void hennir() {

System.out.println("je hennis ! ");

}

public void courir() {

this.vitesses = 30;

System.out.println("Je cours.");

}

public void trotter() {

this.vitesses = 10;

System.out.println("Je trotte.");

}

public void arreter() {

this.vitesses = 0;

System.out.println("Je m'arrete.");

hennir();

}

public String versChaine() {

return "Le cheval s'appele "+nom+", il a "+age+" ans, "+poids+" kg, "+taille+" m, "+"vitesse "+vitesses+" km/h.";

}

}