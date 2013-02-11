Shared API Definition for Project October
=========================================

This project defines the interface for [Project October](https://github.com/ted27/project-october).

The three main components of Project October are:

* Frontend https://github.com/ted27/project-october
* Backend https://github.com/rxc178/project-october-backend
* Thrift API https://github.com/bis12/project-october-api

View the frontend project for more detailed information on Project October.

Updating
--------
Make sure to bump the api version after any changes you make.  Pull request both of the other projects any time you make a backwards-incompatible change.

Running
-------
You will only need to run this if you're actively making changes to the api.

1. Install [thrift](https://thrift.apache.org/)
2. run `make`
