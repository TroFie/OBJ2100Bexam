import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SpillerData {
		StringProperty navn = new SimpleStringProperty();
		StringProperty navn2 = new SimpleStringProperty();
		StringProperty dato = new SimpleStringProperty();
		StringProperty resultat = new SimpleStringProperty();

		public final StringProperty navnProperty() {
			return this.navn;
		}

		public final java.lang.String getNavn() {
			return this.navnProperty().get();
		}

		public final void setNavn(final java.lang.String navn) {
			this.navnProperty().set(navn);
		}

		public final StringProperty navn2Property() {
			return this.navn2;
		}

		public final java.lang.String getNavn2() {
			return this.navn2Property().get();
		}

		public final void setNavn2(final java.lang.String navn2) {
			this.navn2Property().set(navn2);
		}

		public final StringProperty datoProperty() {
			return this.dato;
		}

		public final java.lang.String getDato() {
			return this.datoProperty().get();
		}

		public final void setDato(final java.lang.String dato) {
			this.datoProperty().set(dato);
		}

		/*
		 * public final StringProperty resultatProperty() { return this.resultat; }
		 * 
		 * public final java.lang.String getResultat() { return
		 * this.resultatProperty().get(); }
		 * 
		 * public final void setResultat(final java.lang.String resultat) {
		 * this.resultatProperty().set(resultat); }
		 * 
		 */

	}