package com.dzo.announcerclock.data.sound_repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&\u00a8\u0006\u0005"}, d2 = {"Lcom/dzo/announcerclock/data/sound_repository/SoundOptionRepository;", "", "getSoundOptions", "", "Lcom/dzo/announcerclock/presentation/fragments/sound_fragment/model/SoundOption;", "app_release"})
public abstract interface SoundOptionRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.dzo.announcerclock.presentation.fragments.sound_fragment.model.SoundOption> getSoundOptions();
}