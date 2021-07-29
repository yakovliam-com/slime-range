package com.yakovliam.slimerange.api.cache.loader;

public abstract class CacheLoader<T> {

    /**
     * Cache
     */
    protected final T cache;

    /**
     * Cache
     *
     * @param cache cache
     */
    public CacheLoader(T cache) {
        this.cache = cache;
    }

    public abstract void load();
}
