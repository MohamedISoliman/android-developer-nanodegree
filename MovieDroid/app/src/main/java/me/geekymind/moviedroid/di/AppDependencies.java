package me.geekymind.moviedroid.di;

import android.app.Application;
import me.geekymind.moviedroid.data.MoviesRepo;
import me.geekymind.moviedroid.data.local.DBHelper;
import me.geekymind.moviedroid.data.local.ProviderViewModel;
import me.geekymind.moviedroid.data.remote.MovieRemote;
import me.geekymind.moviedroid.data.remote.RemoteDataFactory;

/**
 * Created by Mohamed Ibrahim on 3/9/18.
 */
public class AppDependencies {

  private AppDependencies() {
  }

  private static DBHelper prefHelperInstance;
  private static ProviderViewModel providerViewModel;

  public static void createGraph(Application application) {
    if (prefHelperInstance == null) {
      prefHelperInstance = new DBHelper(application);
    }
    if (providerViewModel == null) {
      providerViewModel = new ProviderViewModel(application);
    }
  }

  public static DBHelper getPrefHelperInstance() {
    checkNotNull(prefHelperInstance);
    return prefHelperInstance;
  }

  public static ProviderViewModel getProviderViewModel() {
    checkNotNull(providerViewModel);
    return providerViewModel;
  }

  public static MovieRemote getMovieRemote() {
    return RemoteDataFactory.newMovieRemote();
  }

  public static MoviesRepo getMoviesRepo() {
    return MoviesRepo.getInstance();
  }

  private static <T> void checkNotNull(T clazz) {
    if (clazz == null) {
      throw new IllegalStateException("You must initialize AppDependencies graph first");
    }
  }
}
