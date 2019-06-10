package sudoku;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlStageSetup {

    /*------------------------ FIELDS REGION ------------------------*/
    private static Stage stage;

    /*------------------------ METHODS REGION ------------------------*/
    public static Stage getStage() {
        return stage;
    }

    private static void setStage(Stage stage) {
        FxmlStageSetup.stage = stage;
    }

    private static Parent loadFxml(String fxml, ResourceBundle bundle) throws IOException {
        return new FXMLLoader(FxmlStageSetup.class.getResource(fxml), bundle).load();
    }

    /**
     * Method build Stage, set size to Scene, show Stage.
     *
     * @param filePath - String object, holds path to .fxml file
     * @throws IOException required exception to throw
     */
    public static void buildStage(String filePath, ResourceBundle bundle) throws IOException {
        stage.setScene(new Scene(loadFxml(filePath, bundle)));
        stage.sizeToScene();
        stage.show();
    }

    /**
     * Method build Stage, set size to Scene, show Stage.
     *
     * @param filePath - String object, holds path to .fxml file
     * @param title    - String object, holds title of Stage
     * @throws IOException required exception to throw
     */
    public static void buildStage(String filePath, String title, ResourceBundle bundle)
            throws IOException {
        stage.setScene(new Scene(loadFxml(filePath, bundle)));
        stage.setTitle(title);
        stage.sizeToScene();
        stage.show();
    }

    /**
     * Method build Stage, set size to Scene, show Stage.
     *
     * @param stage    Stage pass from start method in main class
     * @param filePath - String object, holds path to .fxml file
     * @param title    - String object, holds title of Stage
     * @throws IOException required exception to throw
     */
    public static void buildStage(Stage stage, String filePath, String title, ResourceBundle bundle)
            throws IOException {
        setStage(stage);
        stage.setScene(new Scene(loadFxml(filePath, bundle)));
        stage.setTitle(title);
        stage.sizeToScene();
        stage.show();
    }
}
