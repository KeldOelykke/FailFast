declare module FailFast {
    /**
     * Checker reference specification.
     * <p>
     * Implement this to manage a reference to a checker.
     * </p>
     *
     * @author Keld Oelykke
     */
    interface ICheckerReference {
        /**
        * Retrieves checker.
        * <p>
        * Call methods of this to check arguments.
        * </p>
        *
        * @return checker.
        */
        getChecker(): IChecker;
        /**
        * Sets the checker.
        * <p>
        * Call methods of this to check arguments.
        * </p>
        *
        * @param checker
        *          checker to use.
        */
        setChecker(checker: IChecker): void;
    }
}
declare module FailFast {
    /**
     * Specification grouping all checker specifications.
     * <p>
     * Each checker should be specified as an interface that can be inherited by this.
     * </p>
     * <p>
     * Implementations of this should be extensible (not final).
     * </p>
     *
     * @author Keld Oelykke
     */
    interface IChecker {
    }
}
declare module FailFast {
    /**
     * Failer specification.
     * <p>
     * The Failer is used to throw fail-fast exceptions when a checker asserts.
     * </p>
     * A checker that asserts starts a contract that this must end (via the call contractor). </p>
     * <p>
     * Threads can poll this to check if a fail-fast exception has been thrown.
     * </p>
     * <p>
     * Implementations of this should be extensible (not final).
     * </p>
     *
     * @author Keld Oelykke
     */
    interface IFailer {
    }
}
declare module FailFast {
    /**
     * FailFast specification.
     * <p>
     * This is to help programmers to follow the FailFast-principle.
     * </p>
     * <p>
     * The FailFast-principle helps you detect and report unexpected errors immediately when they occur.
     * </p>
     * <p>
     * The FailFast-principle favors simple code-fixes over complex runtime error recovery schemes.
     * </p>
     * <p>
     * Read the description and references here: http://en.wikipedia.org/wiki/Fail-fast
     * </p>
     * <p>
     * Implementations of this should be extensible (not final).
     * </p>
     *
     * @author Keld Oelykke
     */
    interface IFailFast extends ICheckerReference, IFailerReference {
    }
}
declare module FailFast {
    /**
     * Failer reference specification.
     * <p>
     * Implement this to manage a reference to a failer.
     * </p>
     * @author Keld Oelykke
     */
    interface IFailerReference {
        /**
         * Retrieves failer.
         * <p>
         * Call methods of this to throw an exception when a check asserts.
         * </p>
         * @return failer.
         */
        getFailer(): IFailer;
        /**
         * Sets the failer.
         * <p>
         * Call methods of this to throw an exception when a check asserts.
         * </p>
         * @param failer
         *          failer to use.
         */
        setFailer(failer: IFailer): void;
    }
}
