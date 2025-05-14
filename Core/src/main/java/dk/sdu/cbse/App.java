package dk.sdu.cbse;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App extends Application{

    public static void main(String[] args) {
        launch(App.class);
    }

    @Override
    public void start(Stage primaryStage) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ModuleConfig.class);

        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        Game game = context.getBean(Game.class);
        game.start(primaryStage);
        game.render();
    }
}
