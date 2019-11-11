package fr.istic.prg1.list.util;

public class List<T extends SuperT> {
	// liste en double chainage par references

	private class Element {
		// element de List<Item> : (Item, Element, Element)
		public T value;
		public Element left, right;

		public Element() {
			value = null;
			left = null;
			right = null;
		}
	} // class Element

	public class ListIterator implements Iterator<T> {
		private Element current;

		private ListIterator() {
			current = flag.right
		}

		@Override
		public void goForward() {
			if (current.right == null) {
				throw new Exception("lelement de droit est vide donc impossible d\'y acceder");
			}
			current = current.right;
		}

		@Override
		public void goBackward() {
			if (current.left == null) {
				throw new Exception("lelement de gauche est vide donc impossible d\'y acceder");
			}
			current = current.left;
		}

		@Override
		public void restart() {
			current = flag.right;
		}

		@Override
		public boolean isOnFlag() {
			return current == flag;
		} // flag=drapaeu

		@Override
		public void remove() {
			try {
				assert current != flag : "\n\n\nimpossible de retirer le drapeau\n\n\n";
			} catch (AssertionError e) {
				e.printStackTrace();
				System.exit(0);
			}

			final Element temp = current;
			final Element droite = current.right;
			final Element gauche = current.left;

			gauche.right = current.right;
			droit.left = current.left;
			current = current.right;
		}

		@Override
		public T getValue() {
			return current.value;
		}

		@Override
		public T nextValue() {
			this.goForward();
			return getValue();
		}

		@Override
		public void addLeft(T v) {
			final Element droite = current;
			final Element gauche = current.left;
			
			current = new Element();
			current.value = v;
			gauche.right = current;
			droite.left = current;
			
		}

		@Override
		public void addRight(T v) {
			final Element gauche = current;
			final Element droite = current.right;
			
			current = new Element();
			current.value = v;
			droite.right = current;
			gauche.left = current;
			
			// goForward()
			// addLeft(v)
			
		}

		@Override
		public void setValue(T v) {
			current.value = v;
		}

		@Override
		public String toString() {
			return "parcours de liste : pas d'affichage possible \n";
		}

	} // class IterateurListe

	private Element flag;

	public List() {
		flag = new Element();
		flag.right = flag;
		flag.left = flag;
	}

	public ListIterator iterator() {
		return new ListIterator();
	}

	public boolean isEmpty() {
		return flag.right == flag;
	}

	public void clear() {
		flag.right = flag; // reviens a suprimer touts les element de la liste cesta du drapeau
		flag.left = flag;
		
	}

	public void setFlag(T v) {
		flag.value = v;
	}

	public void addHead(T v) {
		ListIterator t  = iterator();
		t.addleft(v);
		
	}

	public void addTail(T v) {
		ListIterator t  = iterator();
		goBackward()
		t.addLeft(v);
		
	}

	@SuppressWarnings("unchecked")
	public List<T> clone() {
		List<T> nouvListe = new List<T>();
		ListIterator p = iterator();
		while (!p.isOnFlag()) {
			nouvListe.addTail((T) p.getValue().clone());
			// UNE COPIE EST NECESSAIRE !!!
			p.goForward();
		}
		return nouvListe;
	}

	@Override
	public String toString() {
		String s = "contenu de la liste : \n";
		ListIterator p = iterator();
		while (!p.isOnFlag()) {
			s = s + p.getValue().toString() + " ";
			p.goForward();
		}
		return s;
	}
}
