package dev.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BanqueAppTest {

	// DEBUT DE SESSION
	private EntityManagerFactory emf;
	private EntityManager em;

	// *************______________DEBUT TEST______________*************
	// TEST pour vérifier que les tables et les colonnes ont bien été créées sur
	// WorkBench
	// @Test
	// public void test() {
	//
	// this.emf = Persistence.createEntityManagerFactory("imhotep-banque-app");
	// this.em = emf.createEntityManager();
	//
	// em.close();
	//
	// emf.close();
	// }

	// Le test est passant, les tables ont bien été créées.
	// *************______________FIN TEST______________*************

	@Before
	public void setUp() {

		this.emf = Persistence.createEntityManagerFactory("imhotep-banque-app");
		this.em = emf.createEntityManager();
	}

	// Insérer en base de données des instances des différents objets en
	// utilisant l’EntityManager

	@Test
	public void inserer_donnees_detachees() {

		// Création des variables
		Banque nvelleBanque = new Banque();
		Client nveauClient = new Client();
		Compte nveauCompte = new Compte();
		Operation nvelleOpe = new Operation();
		Adresse nvelleAdresse = new Adresse();

		ArrayList<Operation> operations = new ArrayList<Operation>();
		ArrayList<Client> clients = new ArrayList<Client>();

		clients.add(nveauClient);
		operations.add(nvelleOpe);

		// Insertion de données
		// Banque
		nvelleBanque.setNom("Banque 1");

		// Client
		nveauClient.setNom("Dudule");
		nveauClient.setPrenom("Martine");
		nveauClient.setDateNaissance(LocalDate.of(1980, 4, 3));

		// Compte
		nveauCompte.setNumero(400);
		nveauCompte.setSolde(2000.00);

		// Opération
		nvelleOpe.setDate(LocalDateTime.now());
		nvelleOpe.setMontant(12.50);
		nvelleOpe.setMotif("Virement 1");

		// Adresse
		nvelleAdresse.setCodePostal(53000);
		nvelleAdresse.setNumero(45);
		nvelleAdresse.setRue("Rue de Paris");
		nvelleAdresse.setVille("Laval");

		// Transaction
		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		this.em.persist(nvelleBanque);
		this.em.persist(nveauClient);
		this.em.persist(nveauCompte);
		this.em.persist(nvelleOpe);
		tx.commit();

	}

	// FIN DE SESSION
	@After
	public void tearDown() {

		em.close();
		emf.close();
	}

}
