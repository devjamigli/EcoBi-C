/**
 * Item OnBoarding
 *
 * Elemento base para generar un objeto de tiepo OnBoard
 * Tiene dos atributos escenciales; id del recurso y un titulo
 *
 * @author Jair Migliolo
 * @version 2020.1103
 * @since 1.0
 */

package com.venusdev.ecobi_c.ui.onboarding;

public class OnBoardingItem {
    int imageID;
    String title;

    public OnBoardingItem() {
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
