# Magma-API

[![Metadata-API CI](https://github.com/InseeFr/Magma/actions/workflows/ci.yml/badge.svg)](https://github.com/InseeFr/Magma-API/actions/workflows/ci.yml)
[![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/3702/badge)](https://bestpractices.coreinfrastructure.org/projects/3702)

An API specification for accessing statistical metadata

## Contribute to Magma-API

If you contribute to Magma-API, you may fall in troubles with IntelliJ because the modules [`magma-[diffusion|gestion|commons]-interface`](./interface)
contains generated source code which can be ignored by IntelliJ and cause failures in `magma-[diffusion|gestion|commons]-impl` builds. To prevent this, 
the directory `magma-[diffusion|gestion|commons]-interface/target/generated-sources/openapi/src/main/java` must be marked as a _Generated Sources Root_. This can be done in two steps :
1. Run maven clean then maven install for the whole project (all modules)
2. Two possibilities. Either :
- by executing the action "Reload All Maven Projects" : the directory is marked automatically
- by marking the directory manually : right-click on the directory in project explorer > "Mark directory as"

## .env
In order to use the build the project, you'll need to make sure that you have a .env file at the root of your projet.
The .env file should have the following line :
`PROXY=""` where PROXY refers to the url of your local docker repo. If you do not have a local repo and get your images directly from docker_hub, you can leave it blank.