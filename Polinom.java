import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Polinom {

	private ArrayList<monom> polinom;
	private int grad;

	public int getGrad() {
		return grad;
	}

	public void setGrad(int grad) {
		this.grad = grad;
	}

	public Polinom(ArrayList<monom> monom) {
		super();
		this.polinom = monom;

	}

	public void setare_grad() {
		Polinom p = new Polinom(polinom);
		p.sortare_dupa_grad();
		grad = polinom.get(0).getGrad();

	}

	public ArrayList<monom> getMonom() {
		return polinom;
	}

	public void setMonom(int pozitie, int grad, double coef) {
		for (monom i : polinom) {
			if (i.getGrad() == pozitie) {
				i.setCoef(coef);
				i.setGrad(grad);
			}
		}
	}

	public Polinom suma(Polinom p) {

		if (p.getGrad() == 0 && p.getMonom().get(0).getCoef() == 0) {
			Polinom p1 = new Polinom(polinom);
			p1.setGrad(polinom.get(0).getGrad());
			return p1;

		}

		ArrayList<monom> aux = new ArrayList<monom>();
		Polinom a = new Polinom(polinom);
		a.reducere_termeni_asemenea();

		for (monom i : polinom) {
			aux.add(new monom(i.getCoef(), i.getGrad()));
		}
		for (monom i : p.getMonom()) {
			aux.add(new monom(i.getCoef(), i.getGrad()));
		}
		Polinom rez = new Polinom(aux);

		rez.reducere_termeni_asemenea();
		if (rez.getMonom().isEmpty()) {
			rez.getMonom().add(new monom(0, 0));
		}
		rez.setGrad(rez.getMonom().get(0).getGrad());

		rez.sortare_dupa_grad();
		return rez;
	}

	public void reducere_termeni_asemenea() {
		ArrayList<monom> aux2 = new ArrayList<monom>();
		ArrayList<monom> aux = new ArrayList<monom>();
		ArrayList<Integer> sterg = new ArrayList<Integer>();

		for (monom i : polinom) {
			aux.add(new monom(i.getCoef(), i.getGrad()));

		}

		for (int i = 0; i < polinom.size(); i++) {
			for (int j = i + 1; j < polinom.size(); j++) {
				if (polinom.get(i).getGrad() == polinom.get(j).getGrad()) {
					polinom.get(i).setCoef(polinom.get(i).getCoef() + polinom.get(j).getCoef());

					if (sterg.contains(new Integer(j)) == false) {
						sterg.add(new Integer(j));
					}

				}
			}
		}
		if (sterg.isEmpty() == false) {
			int kk = sterg.get(0).intValue();
			for (Integer i : sterg) {
				polinom.remove(kk);
			}

		}
		for (monom i : polinom) {
			if (i.getCoef() != 0) {
				aux2.add(new monom(i.getCoef(), i.getGrad()));
			}
		}
		polinom.clear();
		for (monom i : aux2) {
			polinom.add(new monom(i.getCoef(), i.getGrad()));
		}

	}

	public ArrayList<Polinom> impartire(Polinom p) throws ExImposibil

	{
		ArrayList<monom> cat = new ArrayList<monom>();
		ArrayList<monom> rest = new ArrayList<monom>();
		Polinom copie_dempartit = new Polinom(polinom);
		copie_dempartit.setGrad(polinom.get(0).getGrad());
		if (this.grad < p.getGrad()) {
			throw new ExImposibil("Primul polinom are gradul mai mic si nu se pot impartii");
		} else {

			if (p.getMonom().isEmpty()) {
				throw new ExImposibil("Nu se poate impartii la 0");
			} else {
				if (this.grad == 0 && p.getGrad() == 0) {
					cat.add(new monom(polinom.get(0).getCoef() / p.getMonom().get(0).getCoef(), 0));
					rest.add(new monom(0, 0));
					copie_dempartit = new Polinom(rest);
				} else {

					if (this.grad >= 1 && p.getGrad() == 0) {
						ArrayList<monom> po = new ArrayList<monom>();
						for (monom i : copie_dempartit.getMonom()) {
							po.add(new monom(i.getCoef() / p.getMonom().get(0).getCoef(), i.getGrad()));

						}

						ArrayList<Polinom> k = new ArrayList<Polinom>();
						Polinom pl = new Polinom(po);
						pl.reducere_termeni_asemenea();
						pl.setGrad(po.get(0).getGrad());
						k.add(pl);
						cat.add(new monom(0, 0));
						k.add(new Polinom(cat));
						return k;
					}

					else {
						copie_dempartit.sortare_dupa_grad();
						p.sortare_dupa_grad();
						while (copie_dempartit.getGrad() >= p.getGrad()) {

							ArrayList<monom> copie_cat = new ArrayList<monom>();
							ArrayList<monom> inter = new ArrayList<monom>();
							double coef = copie_dempartit.getMonom().get(0).getCoef() / p.getMonom().get(0).getCoef();
							int gr = copie_dempartit.getGrad() - p.grad;
							monom termen = new monom(coef, gr);
							cat.add(termen);
							copie_cat.add(termen);
							Polinom pol_copie_cat = new Polinom(copie_cat);
							pol_copie_cat.setGrad(pol_copie_cat.getMonom().get(0).getGrad());
							Polinom intermediar = new Polinom(pol_copie_cat.produs(p).getMonom());
							intermediar.setGrad(intermediar.getMonom().get(0).getGrad());
							copie_dempartit = new Polinom(copie_dempartit.diferenta(intermediar).getMonom());
							copie_dempartit.setGrad(copie_dempartit.getMonom().get(0).getGrad());
							copie_cat.clear();
						}
					}
				}
			}
			Polinom pol_cat = new Polinom(cat);

			pol_cat.setGrad(pol_cat.getMonom().get(0).getGrad());
			ArrayList<Polinom> rez = new ArrayList<Polinom>();
			rez.add(pol_cat);
			rez.add(copie_dempartit);
			// System.out.println(pol_cat.getMonom().get(0).getCoef());
			return rez;
		}

	}

	public Polinom derivare() {

		if (polinom.size() == 1) {
			if (polinom.get(0).getGrad() == 0) {
				ArrayList<monom> s = new ArrayList<monom>();
				s.add(new monom(0, 0));
				Polinom p = new Polinom(s);
				return p;
			}
		}

		Polinom rez = new Polinom(polinom);
		rez.setGrad(rez.getMonom().get(0).getGrad());
		rez.sortare_dupa_grad();
		ArrayList<monom> aux = new ArrayList<monom>();

		for (monom i : rez.getMonom()) {
			if (i.getGrad() != 0) {

				aux.add(new monom(i.getCoef() * i.getGrad(), i.getGrad() - 1));
			}

		}
		Polinom rezultat = new Polinom(aux);
		rezultat.setGrad(rezultat.getMonom().get(0).getGrad());
		return rezultat;
	}

	public Polinom integrare() {
		Polinom rez = new Polinom(polinom);
		rez.setGrad(rez.getMonom().get(0).getGrad());
		rez.sortare_dupa_grad();
		// System.out.println(rez.afis());
		ArrayList<monom> aux = new ArrayList<monom>();

		for (monom i : rez.getMonom()) {
			aux.add(new monom(i.getCoef() / (i.getGrad() + 1), i.getGrad() + 1));
		}
		Polinom rezult = new Polinom(aux);
		rezult.setGrad(rezult.getMonom().get(0).getGrad());
		return rezult;
	}

	public Polinom diferenta(Polinom b) {
		ArrayList<monom> aux = new ArrayList<monom>();
		ArrayList<monom> aux2 = new ArrayList<monom>();

		for (monom i : polinom) {
			aux.add(new monom(i.getCoef(), i.getGrad()));
		}
		for (monom i : b.getMonom()) {
			aux.add(new monom(-i.getCoef(), i.getGrad()));
		}

		Polinom rez = new Polinom(aux);
		rez.reducere_termeni_asemenea();
		rez.sortare_dupa_grad();
		if (rez.getMonom().isEmpty()) {
			rez.getMonom().add(new monom(0, 0));
		}

		rez.setGrad(rez.getMonom().get(0).getGrad());

		return rez;
	}

	public Polinom produs(Polinom p) {
		ArrayList<monom> r = new ArrayList<monom>();

		for (monom i : polinom) {
			for (monom j : p.getMonom()) {
				r.add(new monom(i.getCoef() * j.getCoef(), i.getGrad() + j.getGrad()));
			}
		}
		Polinom rez = new Polinom(r);
		rez.grad = (grad + p.getGrad());
		rez.reducere_termeni_asemenea();
		return rez;
	}

	public void sortare_dupa_grad() {
		for (int i = 0; i < polinom.size() - 1; i++) {
			for (int j = i + 1; j < polinom.size(); j++) {
				if (polinom.get(i).getGrad() < polinom.get(j).getGrad()) {
					Collections.swap(polinom, i, j);
				}
			}
		}
	}

	public String afis() {
		// polinomul sub forma de string
		String s = "";
		Polinom p = new Polinom(polinom);

		p.reducere_termeni_asemenea();
		if (polinom.isEmpty()) {
			return "0";
		}
		p.setGrad(polinom.get(0).getGrad());

		for (monom i : polinom) {
			if (i.getCoef() != 0) {
				if (i.getCoef() - Math.floor(i.getCoef()) != 0) {
					if (i.getGrad() > 1) {
						if (i.getCoef() == 1.0) {
							if (polinom.indexOf(i) == 0) {
								s = s + "x^" + i.getGrad();
							} else {
								s = s + "+" + "x^" + i.getGrad();
							}

						} else {
							if (i.getCoef() == -1.0) {
								s = s + "-" + "x^" + i.getGrad();
							} else {
								if (i.getCoef() > 0) {
									if (polinom.indexOf(i) == 0) {
										s = s + i.getCoef() + "x^" + i.getGrad();
									} else {
										s = s + "+" + i.getCoef() + "x^" + i.getGrad();
									}
								} else {
									s = s + i.getCoef() + "x^" + i.getGrad();
								}
							}
						}
					} else {
						if (i.getGrad() == 1) {
							if (i.getCoef() == 1) {
								if (polinom.indexOf(i) == 0) {
									s = s + "x";
								} else {
									s = s + "+" + "x";
								}
							} else {
								if (i.getCoef() == -1) {

									s = s + "-x";
								} else {
									if (i.getCoef() > 0) {
										if (polinom.indexOf(i) == 0) {
											s = s + i.getCoef() + "x";
										} else {
											s = s + "+" + i.getCoef() + "x";
										}
									} else {
										s = s + i.getCoef() + "x";
									}
								}
							}

						} else {
							if (polinom.indexOf(i) == 0) {
								s = s + i.getCoef();
							} else {

								if (i.getCoef() > 0) {
									s = s + "+" + i.getCoef();
								} else {
									s = s + i.getCoef();
								}

							}
						}

					}
				} else {

					if (i.getGrad() > 1) {
						if (i.getCoef() == 1.0) {
							if (polinom.indexOf(i) == 0) {
								s = s + "x^" + i.getGrad();
							} else {
								s = s + "+" + "x^" + i.getGrad();
							}

						} else {
							if (i.getCoef() == -1.0) {
								s = s + "-" + "x^" + i.getGrad();
							} else {
								if (i.getCoef() > 0) {
									if (polinom.indexOf(i) == 0) {
										s = s + (int) i.getCoef() + "x^" + i.getGrad();
									} else {
										s = s + "+" + (int) i.getCoef() + "x^" + i.getGrad();
									}
								} else {
									s = s + (int) i.getCoef() + "x^" + i.getGrad();
								}
							}
						}
					} else {
						if (i.getGrad() == 1) {
							if (i.getCoef() == 1) {
								if (polinom.indexOf(i) == 0) {
									s = s + "x";
								} else {
									s = s + "+" + "x";
								}
							} else {
								if (i.getCoef() == -1) {

									s = s + "-x";
								} else {
									if (i.getCoef() > 0) {
										if (polinom.indexOf(i) == 0) {
											s = s + (int) i.getCoef() + "x";
										} else {
											s = s + "+" + (int) i.getCoef() + "x";
										}
									} else {
										s = s + (int) i.getCoef() + "x";
									}
								}
							}

						} else {
							if (polinom.indexOf(i) == 0) {
								s = s + (int) i.getCoef();
							} else {

								if (i.getCoef() > 0) {
									s = s + "+" + (int) i.getCoef();
								} else {
									s = s + (int) i.getCoef();
								}

							}
						}

					}

				}

			}
		}

		return s;
	}
}
