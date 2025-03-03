{ pkgs ? import <nixpkgs> {} }:

with pkgs;

mkShell {
  buildInputs = [
    pkgs.scala
    pkgs.metals
    pkgs.sbt
  ];
}
