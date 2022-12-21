// Generated by Dagger (https://dagger.dev).
package com.woojoo.allsearching.domain.usecases;

import com.woojoo.allsearching.domain.repository.ResearchingRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class InsertResearchingUseCase_Factory implements Factory<InsertResearchingUseCase> {
  private final Provider<ResearchingRepository> researchingRepositoryProvider;

  public InsertResearchingUseCase_Factory(
      Provider<ResearchingRepository> researchingRepositoryProvider) {
    this.researchingRepositoryProvider = researchingRepositoryProvider;
  }

  @Override
  public InsertResearchingUseCase get() {
    return newInstance(researchingRepositoryProvider.get());
  }

  public static InsertResearchingUseCase_Factory create(
      Provider<ResearchingRepository> researchingRepositoryProvider) {
    return new InsertResearchingUseCase_Factory(researchingRepositoryProvider);
  }

  public static InsertResearchingUseCase newInstance(ResearchingRepository researchingRepository) {
    return new InsertResearchingUseCase(researchingRepository);
  }
}