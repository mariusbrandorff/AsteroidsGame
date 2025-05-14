package dk.sdu.cbse;

import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.module.ModuleFinder;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Configuration
public class ModuleConfig {
    public ModuleConfig() {
    }

    @Bean
    public Game game() {
        return new Game(getPluginServices(), getPostEntityProcessingServices(), getEntityProcessingServices());
    }

    private ModuleLayer getModuleLayer() {
        ModuleFinder finder = ModuleFinder.of(Paths.get("split-packages"));
        ModuleLayer parent = ModuleLayer.boot();
        List<String> modules = finder.findAll().stream().map(m -> m.descriptor().name()).collect(toList());
        java.lang.module.Configuration config = parent.configuration().resolve(finder, ModuleFinder.of(), modules);
        ModuleLayer layer = parent.defineModulesWithOneLoader(config, ClassLoader.getPlatformClassLoader());
        return layer;
    }

    @Bean
    public Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(getModuleLayer(), IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(getModuleLayer(), IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(getModuleLayer(), IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}
