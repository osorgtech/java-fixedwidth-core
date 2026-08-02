# java-fixedwidth-core

Java library for reading and writing fixed-width text files.

## Building

```
mvn clean install
```

## Releasing

Releases are cut from `master` via git tags — no artifacts are published to a Maven
repository; consumers build from source or reference a tag directly.

1. Push a tag matching `vX.Y.Z` (e.g. `v1.0.0`) on the commit to release.
2. The `release` GitHub Actions workflow runs the build and publishes a GitHub Release
   for that tag automatically.

## License

MIT — see [LICENSE](LICENSE).
