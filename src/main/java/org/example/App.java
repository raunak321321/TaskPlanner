package org.example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.views.ViewBundle;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.SessionFactory;

public class App extends Application<MyConfiguration> {

    private final HibernateBundle<MyConfiguration> hibernateBundle =
            new HibernateBundle<MyConfiguration>(Task.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(MyConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(final Bootstrap<MyConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle<>());
    }

    @Override
    public void run(final MyConfiguration configuration, final Environment environment) {
        final SessionFactory sessionFactory = hibernateBundle.getSessionFactory();

        final TaskDAO taskDAO = new TaskDAO(sessionFactory);

        environment.jersey().register(new HomeResource(taskDAO));
        environment.jersey().register(new FallbackResource());
    }
}
