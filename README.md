# PRP

[![GitHub license](https://img.shields.io/badge/license-GPLv3-blue.svg?style=flat-square)](https://raw.githubusercontent.com/acdcjunior/prp/master/LICENSE)

[![Build Status](https://travis-ci.org/acdcjunior/prp.svg?branch=master)](https://travis-ci.org/acdcjunior/prp)

[![codecov.io](https://codecov.io/github/acdcjunior/prp/coverage.svg?branch=master)](https://codecov.io/github/acdcjunior/prp?branch=master)

[![CircleCI](https://img.shields.io/circleci/project/acdcjunior/prp.svg?style=flat-square)](https://circleci.com/gh/acdcjunior/prp)

parent: [![Dependency Status](https://www.versioneye.com/user/projects/56db690d309a580038affe40/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56db690d309a580038affe40)

prp-boot: [![Dependency Status](https://www.versioneye.com/user/projects/56e610cadf573d0043113b1e/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56e610cadf573d0043113b1e)

prp-domain: [![Dependency Status](https://www.versioneye.com/user/projects/56e610cedf573d003a5f5fa8/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56e610cedf573d003a5f5fa8)

http://yuml.me/edit/d5420fa9

http://plnkr.co/edit/7x5XEnaJLYfXVFjMbwCN

# Infra

## MySQL
- tive que:
    - mysql.cnf
        - `bind-address=0.0.0.0`
        - `lower_case_table_names=1`
    - dar grant para o usario fora do localhost:
    
            mysql -u root
            CREATE USER 'intellij'@'localhost' IDENTIFIED BY 'intellij';
            GRANT ALL PRIVILEGES ON *.* TO 'intellij'@'localhost' WITH GRANT OPTION;
            CREATE USER 'intellij'@'%' IDENTIFIED BY 'intellij';
            GRANT ALL PRIVILEGES ON *.* TO 'intellij'@'%' WITH GRANT OPTION;
    
# Gradle e IntelliJ
- O plugin 'idea' nao presta, pois soh cria file-based, nao directory-based
- Quando voce dah open->import as gradle, ele nao cata direito as build dirs, e um make do intellij (ctrl+f9)
cria um diretorio `out` bizonho na raiz
    - Quando eu dei open sem nada e ele, quando abriu, ofereceu pra importar como gradle, funcionou
     tudo (os filhos gerando tudo na pasta build, independentemente de eu ter usado o intellij ou o gradle itself)
    - Preciso confirmar pra saber se eh repetitivel